package com.example.study;

public class StudyApplication{
    public static void main(String[] args) {
        System.out.println(processString("dsfjh(Ioodood)"));
        System.out.println(processString("abd(jnb)asdf"));
        System.out.println(processString("abdjnbasdf"));
        System.out.println(processString("dd(df)a(ghhh)"));
    }

    public static String processString(String str) {
        StringBuilder validString = new StringBuilder();
        StringBuilder revString = new StringBuilder();
        boolean inReverse = false;

        for (char ch:str.toCharArray()) {
            if(inReverse){
                if(ch==')'){
                    inReverse= false;
                    validString.append(revString.append(ch));
                    revString.delete(0, revString.length());
                    continue;
                }
                revString.insert(0, ch);
                continue;
            }

            if(ch!='('&&ch!=')'){
                validString.append(ch);
            } else if (ch=='(') {
                inReverse= true;
                validString.append(ch);
            }
        }

        return validString.toString();
    }
}
