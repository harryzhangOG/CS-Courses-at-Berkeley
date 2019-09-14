public class MaxArray {
  public static int max(int[] a){
    int i=0;
    int max = 0;
    while (i<a.length){
      if (a[i]>max) {
        max=a[i];
      }
      i++;
    }
    return max;
  }
  public static void main(String[] args){
    int[] arr = {1,2,3,5,5,3,10,4,89,2,100};
    System.out.println(max(arr));
  }

}
