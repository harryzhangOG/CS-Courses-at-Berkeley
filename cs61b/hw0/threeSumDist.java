public class threeSumDist{
  public static boolean distinct(int[] arr){
    int k= arr.length-1;
    for (int p1=0; p1<=k-2; p1++){
      for (int p2=1; p2<=k-1; p2++){
        for (int p3=2; p3<=k; p3++){
          int t = arr[p1]+arr[p2]+arr[p3];
          if (t==0){
            return true;
          }
        }
      }
    }
    return false;

  }
  public static void main(String[] args){
    int[] arr={-6, 3, 10, 200};
    System.out.println(distinct(arr));
  }
}
