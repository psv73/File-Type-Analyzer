package analyzer.model;

public class PatternDBRecord implements Comparable<PatternDBRecord> {

    private int priority;
    private String pattern;
    private String fileType;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public int compareTo(PatternDBRecord patternDBRecord) {
        return Integer.compare(this.priority, patternDBRecord.getPriority());
    }
}
