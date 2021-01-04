import java.io.InputStream;
import java.util.Scanner;

public class Test {
    static Product carts[] = new Product[4];
    static int count = 0;

    public static void main(String[] args) throws ClassNotFoundException {
        boolean bool = true;
        while (bool) {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();//阻塞方法
            System.out.println("请输入密码：");
            String password = sc.next();

            //File file=new File("D:\\IdeaU\\IntelliJ IDEA 2019.2.4\\CmdShop\\src\\users.xlsx");

            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");

            //InputStream inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");

            ReadUserExcel readExcel = new ReadUserExcel();//创建对象
            User users[] = readExcel.readExcel(in);
            /*System.out.println("从excel中读到的用户名: " + users[0].getUsername());
            System.out.println("从excel中读到的密码: " + users[0].getPassword());*/

            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    System.out.println("登录成功！");
                    bool = false;
                    shopping(sc);
                    while (true) {
                        System.out.println("查看购物车      [[  1  ]]");
                        System.out.println("继续购物        [[  2  ]]");
                        System.out.println("结账            [[  3  ]]");
                        System.out.println("退出购物        [[  4  ]]");
                        int choose = sc.nextInt();
                        if (choose == 1) {
                            System.out.println("以下是您购物车内的商品!");
                            for (Product product : carts) {
                                if (product != null) {
                                    System.out.print(product.getpId());
                                    System.out.print("\t" + product.getpName());
                                    System.out.print("\t\t" + product.getPrice());
                                    System.out.println("\t\t\t" + product.getpDesc());
                                }
                            }
                        } else if (choose == 2) {
                            shopping(sc);
                        }else if(choose ==4) {
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println("登录失败！");
                }
            }
        }


    }

    public static void shopping(Scanner sc) throws ClassNotFoundException {

        InputStream inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
        ReadProductExcel readproductExcel = new ReadProductExcel();
        Product products[] = readproductExcel.getAllproduct(inProduct);
        for (Product product : products) {
            System.out.print(product.getpId());
            System.out.print("\t" + product.getpName());
            System.out.print("\t" + product.getPrice());
            System.out.println("\t" + product.getpDesc());
        }
        System.out.println("请输入商品ID,将此商品加入你的购物车!");
        String pId = sc.next();
        ReadProductExcel readProductExcel1 = new ReadProductExcel();
        inProduct = null;
        inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
        Product product = readProductExcel1.getProductById(pId, inProduct);
        if (product != null) {
            carts[count++] = product;
        }

    }
}