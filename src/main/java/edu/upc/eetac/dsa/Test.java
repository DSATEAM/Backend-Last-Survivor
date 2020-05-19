package edu.upc.eetac.dsa;
import edu.upc.eetac.dsa.orm.SessionImpl;
import edu.upc.eetac.dsa.orm.model.Player;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class Test{
        private String name;
        private double aDouble;
        private int anInt;
        private boolean aBoolean;

        public Test(){
            this.name = "22";
            this.aDouble = 22.00345;
            this.aBoolean = true;
            this.anInt = 3;
            //listName.add(new Material("001","wood","Madera",1));
            //listName.add(new Material("002","iron","Hierro",5));
        }
        public void DeleteTest() throws Exception {
            Connection conn=DBUtils.getConnection();
            SessionImpl imp = new SessionImpl(conn);
            Player pl = new Player("Marc","inc", 5, 4, 3, 25);
            imp.save(pl);
            imp.delete(pl);

        }

        public static <T> T convertInstanceOfObject(Object o) {
            try {
                return (T) o;
            } catch (ClassCastException e) {
                return null;
            }
        }
        //Test
        /*Object ret = null;

        Class theClass = object.getClass();
        Method getter = theClass.getMethod("getaDouble");
        // Invoke
        ret = getter.invoke(object);
        if(ret.getClass() == Integer.class){

            System.out.println("Integer" + ret.toString());
        }
        double t = Double.parseDouble((String) ret);
        System.out.println(ret.toString());
        */
        /*Test testobject = new Test();
        Class theClass = testobject.getClass();
        Class secClass = theClass.getClass();
        Field field = theClass.getDeclaredField("listName");
        ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass); // class java.lang.String.
        Object obj = null;
        obj =(List) ObjectHelper.getter(testobject,field.getName());
        //Class<?> cls = Class.forName("String");
        for (Object o :(List) obj) {
            logger.info(o.toString());
            int t = 1;
        }
        ArrayList<String> sFields = new ArrayList<String>();
        */
        /*int i=0;
        for (Field f: fields) {
            if(f.getName().contains("list"))
                sFields.add(f.getName()) ;
        }
        String[] sFieldsArr = new String[sFields.size()];
        sFieldsArr = sFields.toArray(sFieldsArr);
        */
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAnInt() {
            return anInt;
        }

        public void setAnInt(int anInt) {
            this.anInt = anInt;
        }

        public boolean isaBoolean() {
            return aBoolean;
        }

        public void setaBoolean(boolean aBoolean) {
            this.aBoolean = aBoolean;
        }
        public double getaDouble() {
            return aDouble;
        }

        public void setaDouble(double aDouble) {
            this.aDouble = aDouble;
        }
}
