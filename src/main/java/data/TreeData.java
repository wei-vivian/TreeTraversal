package data;

import java.util.List;

public class TreeData {
    private final String startNode;
    private final String endNode;
    private final List<String> nodeList;

    public TreeData(String startNode, String endNode, List<String> nodeList) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.nodeList = nodeList;
    }

    public String getStartNode() {
        return startNode;
    }

    public String getEndNode() {
        return endNode;
    }

    public List<String> getNodeList() {
        return nodeList;
    }
}