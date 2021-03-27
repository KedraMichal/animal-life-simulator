package main;

public class Animal {

    private Vector2d position = new Vector2d(2, 2);
    private MapDirection direction = MapDirection.NORTH;
    private IWorldMap map;

    public Animal(IWorldMap map){
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;

    }


    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public void move(MoveDirection moveDir) {
        switch (moveDir) {
            case FORWARD:
                this.moveForward();
                break;
            case BACKWARD:
                this.direction = this.direction.next().next();
                this.moveForward();
                this.direction = this.direction.next().next();
                break;
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
        }
    }

    private void moveForward() {
        switch (this.direction) {
            case NORTH:
                if(this.map.canMoveTo(this.position.add(new Vector2d(0, 1)))) {
                    this.position = this.position.add(new Vector2d(0, 1));
                    }
                break;
            case SOUTH:
                if (this.map.canMoveTo(this.position.add(new Vector2d(0, -1)))) {
                    this.position = this.position.add(new Vector2d(0, -1));
                }
                break;
            case WEST:
                if (this.map.canMoveTo(this.position.add(new Vector2d(-1, 0)))) {
                        this.position = this.position.add(new Vector2d(-1, 0));
                }
                break;
            case EAST:
                if (this.map.canMoveTo(this.position.add(new Vector2d(1, 0)))) {
                        this.position = this.position.add(new Vector2d(1, 0));
                    }
                break;
        }
    }

    @Override
    public String toString() { // TODO zmienione z org
        switch (this.direction){
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            default:
                return "E";
        }
    }
}
