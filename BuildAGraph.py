import numpy as np
import matplotlib.pyplot as pypt
import PySimpleGUI as psg


def build_gui():
    psg.theme("DarkAmber")
    layout = [
        [psg.Text("Value for parameter a: "), psg.InputText()],
        [psg.Text("Min fi value: "), psg.InputText()],
        [psg.Text("Max fi value: "), psg.InputText()],
        [psg.Text("Number of dots: "), psg.InputText()],
        [psg.Save(), psg.Button("Calculate function"), psg.Button("Cancel")]]
    return layout


def build_graph():
    layout = build_gui()
    window = psg.Window("Лемніската", layout)
    while True:
        events, val = window.read()
        if events == "Save":
            pypt.savefig("lemniscate.png")
            pypt.close()
        elif events in (None, "Cancel", psg.WINDOW_CLOSED):
            break
        elif events == "Calculate function":
            try:
                pypt.clf()
                a = int(val[0])
                min_fi_val = float(val[1])
                max_fi_val = float(val[2])
                dots_num = int(val[3])
            except:
                psg.popup("Error in inserted data")
                break
            fi = np.linspace(min_fi_val, max_fi_val, dots_num)
            r_sq = 2 * (a ** 2) * np.cos(2 * fi)
            r_sq[r_sq < 0] = 0
            x = np.sqrt(r_sq) * np.cos(fi)
            y = np.sqrt(r_sq) * np.sin(fi)

            pypt.plot(x, y, color="blue")
            pypt.xlabel("X axis")
            pypt.ylabel("Y axis")
            pypt.title("Лемніската")
            pypt.show()


build_graph()
