/**
 * from Decimal value 33 to 122 (from ! to z). The characters include major symbols, numbers, and alphabets
 * including upper and lower class. The class can find password up to 6 characters long. The characters are:
 * !"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz
 * @author Rajesh
 *
 */
#include <iostream>

using namespace std;

string getPassword();
string setPassword();
void modifyPassword();

const int SIZE = 6;

int attempt = 0;
int increaseNumber = 0;
int maxAttempt = 4294967295; // max int value in 32 bit computers
string password = "z2!";   //"zzzz" will take 66347100 attempts
string searchPassword = "";
bool found = false;
char passwordArray[SIZE] = {' ', ' ', ' ', ' ', ' ', ' '};

int main()
{
    cout << "Extracting the password..." << endl;
    searchPassword = getPassword();
    cout << "Your password is: " << searchPassword << endl;
    cout << "It took " << attempt << " attempts." << endl;

    return 0;
}

/***
  * getPassword() calls setPassword() which provides a possible matching password
  * and checks with the actual password. The password matching process continues until
  * it reaches the maxAttempt or find the password.
  * @return a String (searchPassword)
  */
string getPassword()
{
    while (!found)
    {
        searchPassword = setPassword();
        if (searchPassword == password){
            found = true;
            cout << "Password found." << endl;
        }
        else if (attempt == maxAttempt){
            cout << "Max attempts (" << maxAttempt << ") reached." << endl;
            cout << "Exiting..." << endl;
            exit(0);
        }
        else{
            attempt++;
            modifyPassword ();
        }
    }
    return searchPassword;
}

/***
  * setPassword() converts an array of char to string and returns the string
  * @return a String (tempPassword)
  */
string setPassword()
{
    string tempPassword = "";
    for (int i = 0; i < SIZE; i++)
        if (passwordArray[i] != ' ')
            tempPassword += passwordArray[i];
    //cout << "TempPassword: " << tempPassword << endl;
    return tempPassword;
}

/***
  * modifyPassword() changes the characters of the array.
  * Basically, the last index of the array will be increased
  * by one ASCII value between the range of '!' to 'a' (33 to 122)
  */
void modifyPassword()
{
    int index = 1;

    passwordArray[SIZE-1] += static_cast<char>(1);

    while (index != SIZE-1)
    {                                  // 90 = 'Z' 123 = { after a
        if (passwordArray[SIZE-index] == 123 && passwordArray[SIZE-index-1] == ' ')
        {
            passwordArray[SIZE-index-1] = 33; // 33 = '!' which is after space (32)
        }
        index++;
    }
    index = 1;
    while (index != SIZE-1)
    {
        if (passwordArray[SIZE-index] == 123){
            passwordArray[SIZE-index-1] += static_cast<char>(1);
            passwordArray[SIZE-index] = 33; // 33 = '!' 32 = ' '
        }
        index++;
    }
}
