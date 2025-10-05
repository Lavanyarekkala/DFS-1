/**
 * Time Complexity: O(m*n) where m is number of rows and n is number of columns(In worst case we may have to traverse all the cells i.e., all cells have same color)
 * Space Complexity: O(m*n) where m is number of rows and n is number of columns(In worst case we may have to store all the cells in the recursion stack i.e., all cells have same color)
 * Did this code successfully run on Leetcode : Yes
 * Any problem you faced while coding this : No
 */

/**
 * The directions array is used to represent the four possible directions (up, down, left, right) in which we can move from a given cell in the image.
 * Each direction is represented as a pair of integers, where the first integer represents the change in the row index (i) and the second integer represents the change in the column index (j).
 * If the new row and column indices are within the bounds of the image and the color of the cell at (r, c) matches the source color(the initial color of the cell at the given row and column in column), we recursively call the helper function on that cell.
 * This process continues until all connected cells with the source color have been changed to the target color. The base case where the given row and column index contains the given color is handled by the if condition in the floodFill method itself.
 */

class floodFill {
    int[][]dirs;
    int sourcecolor;
    int targetcolor;
    int m;
    int n;
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        this.m=image.length;
        this.n=image[0].length;
        this.sourcecolor=image[sr][sc];
        this.targetcolor=color;
        if(sourcecolor==targetcolor) return image;
        this.dirs=new int[][]{{1,0},{0,1},{-1,0},{0,-1}};       
        helper(image,sr,sc);
        return image;
    }
    private void helper(int[][] image,int i,int j)
    {
        image[i][j]=targetcolor;
        for(int[] dir:dirs)
        {
            int r=i+dir[0];
            int c=j+dir[1];
            if(r>=0 && c>=0 && r<m && c<n && image[r][c]==sourcecolor)
            {
                helper(image,r,c);
            }
        }
    }
}