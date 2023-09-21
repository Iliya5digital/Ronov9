 class Converter {
    private static String inWords="";
    private static final String edna = "една ";
    private static final String dve = "две ";
    private static final String thousands ="хиляди ";
    private static final String leva = "лева ";
    private static final String iSpace = "и ";
    private static final String spaceCoins=" стотинки";
    private static final String oneString = "1";
    private static final String threeZeros="000";
    private static final String levaI= "лева и ";
    private static final String edinLevI="един лев и ";
    private static final String ednaStotinka = "1 стотинка";
    private static int zero = 0;
    private static int one = 1;
    private static int two = 2;
    private static int twenty = 20;
    private static int oneHundred = 100;
    private static int oneThousand = 1000;
    private static int tenThousand = 10000;
   
    private static final String unitsBelowHundred[]=  { "",
             "един ",
             "двa ",
             "три ",
             "четири ",
             "пет ",
             "шест ",
             "седем ",
             "осем ",
             "девет ",
             "десет ",
             "единадесет ",
             "дванадесет ",
             "тринадесет ",
             "четиринадесет ",
             "петнадесет ",
             "шестнадесет ",
             "седемнадесет ",
             "осемнадесет ",
             "деветнадесет ",
             "двадесет ",
             "двадесет и един ",
             "двадесет и два ",
             "двадесет и три ",
             "двадесет и четири ",
             "двадесет и пет ",
             "двадесет и шест ",
             "двадесет и седем ",
             "двадесет и осем ",
             "двадесет и девет ",
             "тридесет ",
             "тридесет и един ",
             "тридесет и два ",
             "тридесет и три ",
             "тридесет и четири ",
             "тридесет и пет ",
             "тридесет и шест ",
             "тридесет и седем ",
             "тридесет и осем ",
             "тридесет и девет ",
             "четиридесет ",
             "четиридесет и един ",
             "четиридесет и два ",
             "четиридесет и три ",
             "четиридесет и четири ",
             "четиридесет и пет ",
             "четиридесет и шест ",
             "четиридесет и седем ",
             "четиридесет и осем ",
             "четиридесет и девет ",
             "петдесет ",
             "петдесет и един ",
             "петдесет и два ",
             "петдесет и три ",
             "петдесет и четири ",
             "петдесет и пет ",
             "петдесет и шест ",
             "петдесет и седем ",
             "петдесет и осем ",
             "петдесет и девет ",
             "шейсет ",
             "шейсет и един ",
             "шейсет и два ",
             "шейсет и три ",
             "шейсет и четири ",
             "шейсет и пет ",
             "шейсет и шест ",
             "шейсет и седем ",
             "шейсет и осем ",
             "шейсет и девет ",
             "седемдесет ",
             "седемдесет и един ",
             "седемдесет и два ",
             "седемдесет и три ",
             "седемдесет и четири ",
             "седемдесет и пет ",
             "седемдесет и шест ",
             "седемдесет и седем ",
             "седемдесет и осем ",
             "седемдесет и девет ",
             "осемдесет ",
             "осемдесет и един  ",
             "осемдесет и два ",
             "осемдесет и три ",
             "осемдесет и четири ",
             "осемдесет и пет ",
             "осемдесет и шест ",
             "осемдесет и седем ",
             "осемдесет и осем ",
             "осемдесет и девет ",
             "деветдесет ",
             "деветдесет и един ",
             "деветдесет и два ",
             "деветдесет и три ",
             "деветдесет и четири ",
             "деветдесет и пет ",
             "деветдесет и шест ",
             "деветдесет и седем ",
             "деветдесет и осем ",
             "деветдесет и девет ",
     };
   private static final String tensArray[] = {"",
            "десет ",
            "двадесет ",
            "тридесет ",
            "четиридесет ",
            "петдесет ",
            "шейсет ",
            "седемдесет ",
            "осемдесет ",
            "деветдесет " };
   private static final String hundsArray[] = { " ",
            "сто ",
            "двеста ",
            "триста ",
            "четиристотин ",
            "петстотин ",
            "шестотин ",
            "седемстотин ",
            "осемстотин ",
            "деветстотин " };
    private static final String thousArray[] ={" ",
            "хиляда ",
            "две хиляди ",
            "три хиляди ",
            "четири хиляди ",
            "пет хиляди ",
            "шест хиляди ",
            "седем хиляди ",
            "осем хиляди ",
            "девет хиляди ",
            "десет хиляди "};
 
    private static Integer findFirstNumber(Integer number)
    {
        int firstNumber =
                Integer.parseInt(Integer.toString(number).substring(0,1)); // Връща първото число
        return firstNumber;
    }
 
 
    private static Integer findSecondNumber(Integer number)
    {
        int secondNumber =
                Integer.parseInt(Integer.toString(number).substring(1,2)); // Връща второто число
        return secondNumber;
    }
 
 
    private static Integer findThirdNumber (Integer number)
    {
        int thirdNumber =
                Integer.parseInt(Integer.toString(number).substring(2,3)); // Връща третото число
        return thirdNumber;
    }
 
 
    private static Integer findFirstTwoNumbers(Integer number)
    {
        String firstNumber = findFirstNumber(number).toString();
        String secondNumber = findSecondNumber(number).toString();
        String both = firstNumber+secondNumber;
        return number = Integer.parseInt(both);
    }
 
 
    private static Integer findSecondAndThirdNumber(Integer number)
    {
        String secondNumber = findSecondNumber(number).toString();
        String thirdNumber = findThirdNumber(number).toString();
        String both = secondNumber+thirdNumber;
        return number = Integer.parseInt(both);
    }
 
 
    private static Integer findThousands(Integer number)
    {
        String firstNumber = findFirstNumber(number).toString();
        String secondNumber = findSecondNumber(number).toString();
        String both = firstNumber+secondNumber+threeZeros;
        return number = Integer.parseInt(both);
    }
 
 
    private static String numberToWord(Integer number)
    {
 
        // Ако се дели на 10000
        if ((number/tenThousand) > zero) {
            if (number % findThousands(number) == zero)//82 000, 92 000, 80 000
            {
                if (findFirstTwoNumbers(number)<twenty)
                {
                    inWords+=unitsBelowHundred[findFirstTwoNumbers(number)]+thousands;
                    return numberToWord(number% findThousands(number));
                }
                if (findSecondNumber(number).equals(one)) // 81 000, 21 000
                {
                    inWords+=tensArray[findFirstNumber(number)]+iSpace+edna+thousands; // 81 хиляди
                    return inWords;
                }
                else if (findSecondNumber(number).equals(two))
                {
                    inWords+=tensArray[findFirstNumber(number)]+iSpace+dve+thousands; // 81 хиляди
                    return inWords;
                }
            }
            else
            {
                if (findSecondNumber(number).equals(one)) // 81 000, 21 000
                {
                    inWords+=tensArray[findFirstNumber(number)]+iSpace+edna+thousands; // 81 хиляди
                    return numberToWord(number%findThousands(number));
                }
                else if (findSecondNumber(number).equals(two))
                {
                    inWords+=tensArray[findFirstNumber(number)]+iSpace+dve+thousands; // 81 хиляди
                    return numberToWord(number%findThousands(number));
                }
                else
                {
                    inWords+=unitsBelowHundred[findFirstTwoNumbers(number)]+thousands;
                    return numberToWord(number%findThousands(number));
                }
            }
        }
        if ((number / oneThousand) > zero) {
 
 
            if (number%oneThousand==zero){
                return inWords+=thousArray[findFirstNumber(number)]; // 1000, 2000,3000 ...
            }
 
               inWords+=thousArray[findFirstNumber(number)]; // 4020,5520, 9230 ...
 
                return numberToWord(number%oneThousand);
 
        }
 
        if ((number / oneHundred) > zero)
        {
            if (findSecondNumber(number)==zero && findThirdNumber(number)==zero) // 200, 300 , 500
            {
                return inWords+=iSpace+hundsArray[findFirstNumber(number)];
            }
            else if (findThirdNumber(number)==zero) // 120, 350, 570
            {
                inWords+=hundsArray[findFirstNumber(number)]+iSpace;
                return numberToWord(number%oneHundred);
            }
            else // 226
            {
                inWords+=hundsArray[findFirstNumber(number)];
                return numberToWord(number%oneHundred);
            }
 
        }
 
 
        if (number<oneHundred)
        {
            inWords+=unitsBelowHundred[number];
        }
 
        return inWords;
    }
 
    public static String numbersToWord(Double price){
        if (price == null)
        {
            throw new NullPointerException("Price cannot be null");
        }
        Integer priceIntValue = price.intValue();
        if (priceIntValue == one)
        {
            return edinLevI+findCoins(price)+spaceCoins;
        }
        return numberToWord(priceIntValue) + levaI+findCoins(price);
    }
 
 
 
    public static String findCoins (Double price){
        String wholeNumber = price.toString();
        wholeNumber = wholeNumber.replace(".","-");
        String numbersSplit [] = wholeNumber.split("-");
        String coinsSplit = numbersSplit[1];
        if((oneString).equals(coinsSplit)){
            return ednaStotinka;
        }
        return coinsSplit+ spaceCoins;
    }
}