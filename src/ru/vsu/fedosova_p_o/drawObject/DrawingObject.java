package ru.vsu.fedosova_p_o.drawObject;

import ru.vsu.fedosova_p_o.ScreenConverter;

import java.awt.*;

public interface DrawingObject {
    void draw(Graphics2D graphics2D, ScreenConverter screenConverter);
}
