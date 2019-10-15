public class MyClass {
    public static void main(String[] args) {

        Hello Anthony = new Hello();
        Anthony.setAge(16);
        Anthony.setName("Anthony");
        System.out.println(Anthony.getAge()+Anthony.getName());
        System.out.println(Anthony.getNoOfStudents());

        Hello Tom = new Hello();
        System.out.println(Tom.getNoOfStudents());

        System.out.println(Hello.NoOfStudents);

    }
    }