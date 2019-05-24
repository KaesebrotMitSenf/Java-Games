class Crab {
	constructor(x, y) {
		this.x = x;
		this.y = y;
		this.image = [];
		this.image[0] = new Image();
		this.image[1] = new Image();
		this.image[0].src = "crab.png";
		this.image[1].src = "crab2.png";

		this.direction = "left";
		this.movement = 0;

		this.imageCounter = 0;
		this.timeCounter = 0;
	}

	update() {
		if (this.timeCounter < 15) {
			this.timeCounter += 1;
		} else {
			this.timeCounter = 0;
			this.imageCounter += 1;
			if (this.imageCounter == this.image.length) {
				this.imageCounter = 0;
			}
		}

		if (this.direction == "left") {
			this.x -= 1
			this.movement += 1;
		} else {
			this.x += 1
			this.movement += 1;
		}

		if (this.movement > 50) {
			if (this.direction == "left") {
				this.direction = "right";
			} else {
				this.direction = "left";
			}
			this.movement = 0;
		}
	}

	draw(ctx) {
		if (this.direction == "left") {
			ctx.drawImage(this.image[this.imageCounter], this.x - px, this.y - py, this.image[this.imageCounter].width, this.image[this.imageCounter].height);
		} else {
			ctx.drawImage(this.image[this.imageCounter], this.x - px, this.y - py, this.image[this.imageCounter].width, this.image[this.imageCounter].height);
		}

	}
}