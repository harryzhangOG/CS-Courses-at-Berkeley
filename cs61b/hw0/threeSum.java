public class threeSum{
  public static boolean threeSum(int[] arr){
    for (int p1:arr){
      for (int p2:arr){
        for (int p3:arr){
          int k=p1+p2+p3;
          if (k==0){
            return true;
          }
        }
      }
    }
    return false;
  }
public static void main(String[] args){
  int[] arr={-6, 3, 10, 200};
  System.out.println(threeSum(arr));
}
}
