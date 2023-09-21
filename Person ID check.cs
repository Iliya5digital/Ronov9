using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
 
namespace HomeworkOOP
{
    enum Gender { Male, Female};
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter ID: ");
            string userID = Console.ReadLine();
            Gender gender;
            if (userID.Length != 10)
                {
                Console.WriteLine("Wrong! You entered incorrect ID:  {0}",userID);
                Console.ReadKey();
                Environment.Exit(0);
                }
            Console.WriteLine(User(userID, out gender));
            Console.ReadKey();
        }
 
        static string User(string userInput, out Gender gender)
        {
            string ID="";
            int year = int.Parse(userInput.Substring(0,2));
            int month = int.Parse(userInput.Substring(2,2));
            int day = int.Parse(userInput.Substring(4,2));
            int numNine = int.Parse(userInput.Substring(8,1));
            if (month >= 1 && month <= 12)
            {
                ID = "19" + year + "." + month + "." + day;
            }
            else if (month >= 21 && month <= 32)
            {
                month -= 20;
                ID = "18" + year + "." + month + "." + day;
            }
            else if (month >= 41 && month <= 52)
            {
                month -= 40;
                ID = "20" + year + "." + month + "." + day;
            }
            else
            {
                Console.WriteLine("Invalid month, your month cannot be: {0}",month);
                Console.ReadKey();
                Environment.Exit(0);
            }
            if (day <= 0 && day >= 32)
            {
                Console.WriteLine("Invalid day, your day cannot be: {0}", day);
                Console.ReadKey();
                Environment.Exit(0);
            }
 
            if (numNine % 2 == 0)
            {
                gender = Gender.Male;
            }
            else gender = Gender.Female;
 
            ID = ("Birth date: " + ID + " Gender:" + gender);
            return ID;
        }
    }
}