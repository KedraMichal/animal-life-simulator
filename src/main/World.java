package main;

public class World {

    public static void main(String[] args){
        String[] arg = new String[]{"f", "b", "r", "l", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parser(arg);
        IWorldMap  map = new GrassField(5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };

        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        System.out.println(map);


//        GrassField map2 = new GrassField(10);
//
//        Vector2d[] positions2 = { new Vector2d(2,2), new Vector2d(3,4) };
//        IEngine engine = new SimulationEngine(directions, map2, positions2);
//        engine.run();
//        for (Animal a: map.animals){
//            System.out.println(a);
//        }
//        System.out.println(map.toString());

//        Animal a1 = new Animal();
//        System.out.println(a1);
//
//        String[] commands = new String[]{"r", "Xd", "f", "f","f"};
//
//        OptionsParser opt = new OptionsParser();
//        MoveDirection[] moves = opt.parser(commands);
//        for (MoveDirection move_made: moves){
//            a1.move(move_made);
//        }
//        System.out.println(a1);


//        System.out.println("system wystartował");
//        Direction[] arr = {Direction.F, Direction.F};
//        run(arr);
//        System.out.println("system zakonczyl");
    }

//    public static void run(Direction[] arr){
//
//        for (Direction com: arr){
//            switch (com){
//                case F:
//                    System.out.println("zwierzak idzie do przodu");
//                    break;
//                case B:
//                    System.out.println("tyl");
//                    break;
//                case L:
//                    System.out.println("w lego");
//                    break;
//                case R:
//                    System.out.println("prawo");
//                    break;
//            }

//        for (int i=0; i<arr.length; i++){
//            if (i == arr.length - 1){
//                System.out.print(arr[i]);
//                System.out.println();
//            }
//            else{
//                System.out.print(arr[i] + ", ");
//            }

}


