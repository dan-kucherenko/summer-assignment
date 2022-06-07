import numpy as np
import matplotlib.pyplot as plt

a = int(input("Enter the value of parameter 'a': "))
print("Inserted value of 'a' is: ", a)

fi = np.linspace(0, 2 * np.pi)
r = pow(2 * pow(a, 2) * np.cos(2*fi))
x = np.array(np.sqrt(r) * np.cos(fi))
y = np.array(np.sqrt(r) * np.sin(fi))


