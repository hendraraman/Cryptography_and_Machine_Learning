# Cipher Text Classifier
This project aims to classify cipher texts into two categories: those generated through keyword columnar transposition and randomly generated text.

Installation:
To use this project, you will need to install the following dependencies:
!pip install PyPDF2
!pip install keras-tuner

## Approach

To create cipher text from plain text (several books are used here to generate the cipher text)
## The following functions were used to generate the cipher text
## matrix_creation_for_plaintext() //to create a matrix for the plain text
![image](https://github.com/hendraraman/Cryptography_and_Machine_Learning/assets/90880440/53085b9c-cbde-4af6-a673-71fd76ede007)

## cipher_generation() // given the matrix and the key, it returns the cipher text (by keyword columnar transposition)
![image](https://github.com/hendraraman/Cryptography_and_Machine_Learning/assets/90880440/6c754a24-6cb6-4f2c-b5c7-0325710bc493)

## Each letter is replaced by it's ASCII value and is provided to the machine learning and deep learning models

## Confusion Matix for the Convolutional Neural Network model
![Untitled](https://github.com/hendraraman/Cryptography_and_Machine_Learning/assets/90880440/5b896377-0510-4d2d-923f-3b32f1fa4f14)





