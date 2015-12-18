package Windows;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Square extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int value;
	private boolean changed;

	public Square() {
		super();
		value = 0;
		changed = false;

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public void setImage(String des) {
		this.setIcon(new ImageIcon(des));
	}

	public void resetImage(boolean stars) {
		if (!stars) {
			if (this.value == 0)
				this.setImage("Images\\white.png");
			if (this.value == 2)
				this.setImage("Images\\2.png");
			if (this.value == 4)
				this.setImage("Images\\4.png");
			if (this.value == 8)
				this.setImage("Images\\8.png");
			if (this.value == 16)
				this.setImage("Images\\16.png");
			if (this.value == 32)
				this.setImage("Images\\32.png");
			if (this.value == 64)
				this.setImage("Images\\64.png");
			if (this.value == 128)
				this.setImage("Images\\128.png");
			if (this.value == 256)
				this.setImage("Images\\256.png");
			if (this.value == 512)
				this.setImage("Images\\512.png");
			if (this.value == 1024)
				this.setImage("Images\\1024.png");
			if (this.value == 2048)
				this.setImage("Images\\2048.png");
			if (this.value == 4096)
				this.setImage("Images\\4096.png");
			if (this.value == 8192)
				this.setImage("Images\\8192.png");
			if (this.value == 16384)
				this.setImage("Images\\16384.png");
			if (this.value == 32768)
				this.setImage("Images\\32768.png");
			if (this.value == 65536)
				this.setImage("Images\\65536.png");
		} else {
			if (this.value == 0)
				this.setImage("Images\\white.png");
			if (this.value == 2)
				this.setImage("Images\\2star.png");
			if (this.value == 4)
				this.setImage("Images\\4star.png");
			if (this.value == 8)
				this.setImage("Images\\8star.png");
			if (this.value == 16)
				this.setImage("Images\\16star.png");
			if (this.value == 32)
				this.setImage("Images\\32star.png");
			if (this.value == 64)
				this.setImage("Images\\64star.png");
			if (this.value == 128)
				this.setImage("Images\\128star.png");
			if (this.value == 256)
				this.setImage("Images\\256star.png");
			if (this.value == 512)
				this.setImage("Images\\512star.png");
			if (this.value == 1024)
				this.setImage("Images\\1024star.png");
			if (this.value == 2048)
				this.setImage("Images\\2048star.png");
			if (this.value == 4096)
				this.setImage("Images\\4096star.png");
			if (this.value == 8192)
				this.setImage("Images\\8192star.png");
			if (this.value == 16384)
				this.setImage("Images\\16384star.png");
			if (this.value == 32768)
				this.setImage("Images\\32768star.png");
			if (this.value == 65536)
				this.setImage("Images\\65536star.png");
		}

	}
}
