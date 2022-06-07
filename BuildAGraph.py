import numpy as np
import matplotlib.pyplot as plot

a = int(input("Enter the value for parameter 'a': "))
print("Entered values is: ", a)

fi = np.linspace(0, 2 * np.pi)
r = pow(2 * pow(a, 2) * np.cos(2 * fi))

x = np.array(r * np.cos(fi))
y = np.array(r * np.sin(fi))
