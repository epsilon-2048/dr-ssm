package epsilon.ssm.bean;

import java.util.Scanner;

public class Category {
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    /*
   static int search(int[] a,int key){
        for(int i = a.length/2; i < a.length || i>-1;){
            if (key <= a[i]){
                if(i == 0 || key > a[i-1])
                    return i+1;
                else{
                    i = i / 2;
                }
            }else{
                i = (a.length+i)/2;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0;i<n; i++){
            a[i] = scanner.nextInt();
            if (i != 0)
                a[i] += a[i-1];
        }
        int m = scanner.nextInt();
        for(int i = 0; i< m; i++){
            System.out.println(search(a,scanner.nextInt()));
        }
    }*/

}