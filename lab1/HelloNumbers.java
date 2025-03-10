public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int sum = 0; // 用于存储累加和
        while (x < 10) {
            sum += x; // 累加当前 x 的值
            System.out.print(sum + " "); // 打印累加和
            x = x + 1;
        }
        System.out.println(); // 打印换行
    }
}
