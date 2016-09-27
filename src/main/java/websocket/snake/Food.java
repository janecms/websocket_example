package websocket.snake;

public class Food {
    private  String hexColor="#ffffff";
    private boolean on=true;
    private Location location;
    /**
     * 变换颜色和位置
     */
    public void reset(){
        this.hexColor="#ffffff";
        Location location = SnakeAnnotation.getRandomLocation();
        this.location=location;
        this.on=true;
    }

    public static Food newFood(){
        Food food = new Food();
        food.reset();
        return food;
    }
    /**
     * 实现闪烁效果
     */
    public void updateColor(){
        if(this.on){
            this.hexColor="#ffffff";
            this.on=false;
        }else{
            this.hexColor="#000000";
            this.on=true;
        }
    }

    public Location getLocation() {
        return location;
    }

    public String getHexColor() {
        return hexColor;
    }
    /**
     * 广播信息
     */
    public void broadcastMe() {
        this.updateColor();
        String msg =String.format("{'type': 'update_food', 'data' : [%s]}",this.getJson());
        for (Snake snake : SnakeTimer.getSnakes()) {
            try {
                snake.sendMessage(msg);
            } catch (IllegalStateException ise) {
            }
        }
    }

    public synchronized String getJson(){
        String json=String.format("{color: '%s',x: %d, y: %d}",this.hexColor, this.location.x, this.location.y);
        return json;
    }
}
