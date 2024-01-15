import java.io.*;
import java.util.Scanner;

public class Var4 {
    public static void main(String[] args) throws IOException{
        DataOutputStream dout1 = null;
        DataOutputStream dout2 = null;
        DataInputStream din = null;
        DataInputStream din2 = null;
        try{
            File src = new File("src.txt");
            if(src.exists()) src.delete();
            src.createNewFile();
            Scanner sc = new Scanner(System.in);
            System.out.println("count");
            int count = sc.nextInt();
            dout1 = new DataOutputStream(new FileOutputStream(src));
            for (int i = 0; i < count; i++){
                float f = sc.nextFloat();
                dout1.writeFloat(f);
            }
            din = new DataInputStream(new FileInputStream(src));
            File res = new File("res");
            if (res.exists()) res.delete();
            res.createNewFile();
            dout2 = new DataOutputStream(new FileOutputStream(res));
            int countOtr = 0;
            for (int i = 0; i < count; i++){
                float f = din.readFloat();
                if(f<0){
                    dout2.writeFloat(f);
                    countOtr++;
                }
            }
            dout2.writeInt(countOtr);
            din2 = new DataInputStream(new FileInputStream(res));
            for (int i = 0; i < countOtr; i++){
                System.out.println(din2.readFloat() + " ");
            }
            System.out.println("число отрицательных чисел = " + din2.readInt());
        }
        catch(IOException io){
            io.printStackTrace();
        }
        finally{
            dout1.flush();
            dout2.close();
            din.close();
            din2.close();
        }
    }
}
