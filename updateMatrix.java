/** Time Complexity: O(m*n) where m is number of rows and n is number of columns(In worst case we may have to traverse all the cells i.e., all cells have 1)
 *  Space Complexity: m+n (At each point we take the recursion to right and bottom only since the left and top would have been already computed and memoized)
 *  Did this code successfully run on Leetcode : Yes
 *  Any problem you faced while coding this : No
 */

/**
 * This is a DFS with memoization problem. We traverse through each cell in the matrix and whenever we encounter a 1, we do a DFS to find the nearest 0.
 * We only traverse to the right and bottom since the left and top would have been already computed and memoized.
 * The result matrix is initialized with 0s and the cells with 1s are updated with the distance to the nearest 0.
 * The base case for the DFS is when we encounter a 0 in any of its neighbors in given matrix mat return 1. We will reuse the values in left and top since they would have been already computed and memoized, if the right and bottom are not computed i.e., they are 0 in result matrix but are 1 in given matrix mat, we will take the recursive call to compute them and store them in result matrix.
 * Once all 4 neighbors are computed, we will take the minimum of all 4 neighbors and add 1 to it to get the distance to the nearest 0 for the current cell.
 */

class updateMatrix {
    int m;
    int n;
    int[][] result;
    public int[][] updateMatrix(int[][] mat) {
        this.m=mat.length;
        this.n=mat[0].length;
        this.result=new int[m][n];
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(mat[i][j]==1)
                {
                    result[i][j]=dfs(mat,i,j);
                }
            }
        }
       
       return result;
        
    }
    private int dfs(int[][] mat,int row,int column)
    {
        if(row>0 && mat[row-1][column] == 0) return 1;
        if(row < m-1 && mat[row+1][column] == 0) return 1;
        if(column>0 && mat[row][column-1] == 0) return 1;
        if(column < n-1 && mat[row][column+1] == 0) return 1;
        int top=9999, left =9999, bottom=9999, right= 9999;

       if(row-1>=0 && result[row-1][column]!=0)
       {
         top=result[row-1][column];
       }
       if(column-1>=0 && result[row][column-1]!=0)
       {
          left=result[row][column-1];
       }
       if(column+1<n)
       {
            if(result[row][column+1]==0)
            {
                result[row][column+1]=dfs(mat,row,column+1);
            }
            right=result[row][column+1];
       }
       if(row+1<m)
       {
            if(result[row+1][column]==0)
            {
                result[row+1][column]=dfs(mat,row+1,column);
            }
            bottom=result[row+1][column];
       }

       return Math.min(left, Math.min(right, Math.min(bottom,top)))+1;
    }
}