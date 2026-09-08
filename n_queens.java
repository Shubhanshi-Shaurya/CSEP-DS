import java.util.*;;

public class n_queens {


    public static  Boolean check(char[][] board,int row,int col){
        //check row
        int n=board.length;
        for(int j=0;j<col;j++){
            if(board[row][j]=='Q') return false;
        }
        //check col
        for(int j=0;j<row;j++){
            if(board[j][col]=='Q') return false;
        }
        //check northeast
        int i=row;
        int j=col;
        while(i>=0 && j<n){
            if(board[i][j]=='Q') return false;
            i--;
            j++;
        }
        //check northwest
        i=row;
        j=col;
        while(i>=0 && j>=0){
            if(board[i][j]=='Q') return false;
            i--;
            j--;
        }
        return true;
    }


    public static  void helper(char[][] board,int row,List<List<String>> ans){
        if(row==board.length){
            List<String> temp=new ArrayList<>();
            for(int i=0;i<board.length;i++){
                StringBuilder s=new StringBuilder();
                for(int j=0;j<board.length;j++){
                    s.append(board[i][j]);
                }
                temp.add(s.toString());
            }
            ans.add(temp);
            return;
        }
        for(int j=0;j<board.length;j++){
            if(check(board,row,j)){
                board[row][j]='Q';
                helper(board,row+1,ans);
                board[row][j]='.';
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the dimension of Chessboard : ");
        int n=sc.nextInt();
        char[][] board=new char[n][n];
        List<List<String>> ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                board[i][j]='.';
            }
        }
        helper(board,0,ans);
        sc.close();
    }
}
