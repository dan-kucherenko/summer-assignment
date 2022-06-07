import numpy as np
import matplotlib.pyplot as plt
import PySimpleGUI as psg


def build_gui():
    layout = [
        [psg.Text("Enter the value for parameter 'a': "), psg.InputText()],
        [psg.Save()],
        [psg.Submit(), psg.Cancel()]]

    window = psg.Window("Лемніската", layout)


def build_graph():
    a = int(input("Enter the value for parameter 'a': "))
    print("Entered values is: ", a)

    fi = np.linspace(0, 2 * np.pi, 1000)
    r_sq = pow(2 * pow(a, 2) * np.cos(2 * fi), 2)

    x = np.array(np.sqrt(r_sq) * np.cos(fi))
    y = np.array(np.sqrt(r_sq) * np.sin(fi))

    plt.plot(x, y, color='blue')
    plt.title("Лемніската")
    plt.grid()
    plt.show()


build_graph()
