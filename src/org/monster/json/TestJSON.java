package org.monster.json;

import java.lang.reflect.Field;

/**
 * @author Monster
 */
public class TestJSON {

    public static void main(String[] args) {
        TestJSON testJSON = new TestJSON();
        testJSON.test();
    }

    public void test() {

        ObjectA objectA = new ObjectA();
        objectA.booleanType = true;
        objectA.byteType = 1;
        objectA.shortType = 2;
        objectA.characterType = 'C';
        objectA.stringType = "S";
        objectA.integerType = 4;
        objectA.floatType = 4f;
        objectA.longType = 8L;
        objectA.doubleType = 8d;

        JSON json = new JSON();
        json.toJSONString(objectA);
    }

    class ObjectA {
        public Boolean booleanType;
        public Byte byteType;
        public Short shortType;
        public Character characterType;
        public String stringType;
        public Integer integerType;
        public Float floatType;
        public Long longType;
        public Double doubleType;
    }

    class JSON {

        public String toJSONString(Object object) {
            Class<?> aClass = object.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                Object value = null;
                try {
                    value = field.get(object);
                } catch (IllegalAccessException e) {
                    //Ignore
                }
                if (value == null) {
                    continue;
                }
                if (field.getType() == Boolean.class) {
                    System.out.print("{");
                    System.out.print("\"" + field.getName() + "\"" + ":" + "\"" + String.valueOf(value) + "\"");
                    System.out.print("}");
                } else if (field.getType() == Byte.class) {
                    System.out.print("{");
                    System.out.print("\"" + field.getName() + "\"" + ":" + Byte.toString((Byte) value));
                    System.out.print("}");
                } else if (field.getType() == Short.class) {
                    System.out.print("{");
                    System.out.print("\"" + field.getName() + "\"" + ":" + Short.toString((Short) value));
                    System.out.print("}");
                } else if (field.getType() == Character.class) {
                    System.out.print("{");
                    System.out.print("\"" + field.getName() + "\"" + ":" + "\"" + value + "\"");
                    System.out.print("}");
                } else if (field.getType() == String.class) {
                    System.out.print("{");
                    System.out.print("\"" + field.getName() + "\"" + ":" + "\"" + value + "\"");
                    System.out.print("}");
                } else if (field.getType() == Integer.class) {
                    System.out.print("{");
                    System.out.print("\"" + field.getName() + "\"" + ":" + Integer.toString((Integer) value));
                    System.out.print("}");
                } else if (field.getType() == Float.class) {
                    System.out.print("{");
                    System.out.print("\"" + field.getName() + "\"" + ":" + Float.toString((Float) value));
                    System.out.print("}");
                } else if (field.getType() == Long.class) {
                    System.out.print("{");
                    System.out.print("\"" + field.getName() + "\"" + ":" + Long.toString((Long) value));
                    System.out.print("}");
                } else if (field.getType() == Double.class) {
                    System.out.print("{");
                    System.out.print("\"" + field.getName() + "\"" + ":" + Double.toString((Double) value));
                    System.out.print("}");
                } else {
                    toJSONString(value);
                }
            }
            return null;
        }
    }
}
