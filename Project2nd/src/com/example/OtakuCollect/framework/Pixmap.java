package com.example.OtakuCollect.framework;

import com.example.OtakuCollect.framework.Graphics.PixmapFormat;

public interface Pixmap {
	
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
