import numpy as np
import matplotlib.pyplot as pypt
import PySimpleGUI as psg


def build_gui():  # creating a GUI
    psg.theme("DarkBlue")
    layout = [
        [psg.Text("Lemniscte function: r^2 = 2a^2*cos(2 * fi)")],
        [psg.Text("Value for parameter a:"), psg.InputText()],
        [psg.Text("Min fi value:"), psg.InputText()],
        [psg.Text("Max fi value:"), psg.InputText()],
        [psg.Text("Step:"), psg.InputText()],
        [psg.Save(), psg.Button("Calculate function"), psg.Button("Cancel")]]
    return layout


def build_graph():
    layout = build_gui()
    window = psg.Window("Лемніската", layout, size=(350, 175))
    while True:
        events, val = window.read()
        if events == "Save":
            pypt.savefig("lemniscate.png")
            pypt.close()
        elif events in (None, "Cancel", psg.WINDOW_CLOSED):
            break
        elif events == "Calculate function":
            try:
                pypt.clf()  # clear the window with graph in case any graph was already there
                # initialise values
                a = int(val[0])
                min_fi_val = float(val[1])
                max_fi_val = float(val[2])
                step = float(val[3])
            except:
                psg.popup("Error in inserted data")
                break

            fi = np.linspace(min_fi_val, max_fi_val, int((max_fi_val - min_fi_val) / step))
            r_sq = 2 * (a ** 2) * np.cos(2 * fi)
            r_sq[r_sq < 0] = 0
            x = np.sqrt(r_sq) * np.cos(fi)
            y = np.sqrt(r_sq) * np.sin(fi)

            pypt.plot(x, y, color="blue")  # plot a graph
            # add axis labels and title of the graph
            pypt.axhline(y=0, c="black", label = "y = 0")
            pypt.axvline(x=0, c="black", label = "x = 0")
            pypt.grid()
            pypt.xlabel("X axis")
            pypt.ylabel("Y axis")
            pypt.title("Лемніската")
            pypt.show()


build_graph()
