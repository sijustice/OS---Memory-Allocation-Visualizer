package oop;

public class Block {
    private int start;
    private int size;
    private String status;     // "free" or "allocated"
    private String processId;  // null if free

    public Block(int start, int size, String status, String processId) {
        this.start = start;
        this.size = size;
        this.status = status;
        this.processId = processId;
    }

    public int getStart() { return start; }
    public void setStart(int start) { this.start = start; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getProcessId() { return processId; }
    public void setProcessId(String processId) { this.processId = processId; }

    public boolean isFree() {
        return "free".equalsIgnoreCase(status);
    }

    @Override
    public String toString() {
        if (isFree()) {
            return String.format("[FREE  | start=%d, size=%d]", start, size);
        }
        return String.format("[BLOCK | start=%d, size=%d, process=%s]", start, size, processId);
    }
}