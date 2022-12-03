package app;

import data.TreeData;
import util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppTreeTraversal {
    public static final int BRANCH_COUNT = 2;
    public static final int ROOT_INDEX = 1;
    public static final String UP_SIGN = "上";
    public static final String RIGHT_SIGN = "右";
    public static final String LEFT_SIGH = "左";
    public static final String SPACE = " ";
    public final List<String> inputList;

    public static void main(String[] args) {
        String folder = "D:\\WorksSpace\\Java\\TreeTraversal\\src\\main\\resources";
        String inputPath = folder + "\\input.txt";
        AppTreeTraversal appTreeTraversal = new AppTreeTraversal(inputPath);
        appTreeTraversal.traversal();
    }

    public AppTreeTraversal(String inputPath) {
        inputList = FileUtil.readFile(inputPath);
    }

    public void traversal() {

        // 1. Build tree
        List<TreeData> treeList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); ) {
            String startNode = inputList.get(i++);
            String endNode = inputList.get(i++);
            String[] nodeData = inputList.get(i++).split(SPACE);
            List<String> nodeList = new ArrayList<>(Arrays.asList(nodeData));
            treeList.add(new TreeData(startNode,
                    endNode,
                    nodeList));
        }

        // 2. Get each tree's path from start node to end node
        for (TreeData treeData : treeList) {
            // 2.1. Get path of start/end node from root
            List<String> startNodePath = getPathFromRoot(treeData.getNodeList()
                    .indexOf(treeData.getStartNode()));
            List<String> endNodePath = getPathFromRoot(treeData.getNodeList()
                    .indexOf(treeData.getEndNode()));

            // 2.2. Find path from start node to end node
            List<String> path = getPathFromStartToEnd(startNodePath, endNodePath);

            // 2.3. Output path from start node to end node
            System.out.println(path);
        }
    }

    private List<String> getPathFromRoot(int nodeIndex) {
        List<String> path = new ArrayList<>();
        int tempIndex = nodeIndex;
        while (ROOT_INDEX < tempIndex) {
            if (0 == tempIndex % BRANCH_COUNT) {
                path.add(LEFT_SIGH);
            } else {
                path.add(RIGHT_SIGN);
            }
            tempIndex = tempIndex / BRANCH_COUNT;
        }
        Collections.reverse(path);
        return path;
    }

    private List<String> getPathFromStartToEnd(List<String> startPath, List<String> endPath) {
        List<String> path = new ArrayList<>();
        boolean commonFlag = true;
        for (int i = 0; i < startPath.size() && i < endPath.size() && commonFlag; i++) {
            if (startPath.get(i).equals(endPath.get(i))) {
                startPath.remove(i);
                endPath.remove(i);
            } else {
                commonFlag = false;
            }
        }
        for (int i = 0; i < startPath.size(); i++) {
            path.add(UP_SIGN);
        }
        path.addAll(endPath);
        return path;
    }
}