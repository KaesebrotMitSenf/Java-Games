class Cloud {
	constructor(x, y) {
		this.x = x;
		this.y = y;
		this.loadimg = new Image();
		this.loadimg.src = "cloud.png";
	}

	update() {
		if (this.x < 0) {
			this.x = 128 * 32 + this.loadimg.width;
			this.y = Math.random() * 800;
		} else {
			this.x -= 1;
		}
	}

	draw(ctx) {
		ctx.drawImage(this.loadimg, this.x - px, this.y - py, this.loadimg.width, this.loadimg.height);
	}
}