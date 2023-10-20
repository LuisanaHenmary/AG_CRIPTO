import java.util.Random;

public class GeneticAlgorithm {

    static double sum = 0;
    static String [][] population;
    static String [][] temporary_population;
    static String [] couples;
    static String [] winners;

    static int rows, columns, num_wallets;

    public GeneticAlgorithm(int r, int n_winners, int nw) {
        rows = r;
        columns = 4; //[fila] [individuo] [aptitud] [adaptabilidad]
        population = new String[rows][columns];
        temporary_population = new String[rows][columns];
        couples = new String[rows];
        winners = new String[n_winners];
        num_wallets = nw;
    }

    public void initPopulation(){
        // Inicializa la poblacion

        System.out.println("Inicializando población...");
        String individual;
        Random ri = new Random();

        for (int i = 0; i < couples.length; i++) {
            individual = "";

            for (int j = 0; j < num_wallets; j++) {
                // Cada individuo es un vector de xi, donde xi es un gen y esta entre 0 y 1
                individual += Double.toString(ri.nextDouble(1.0)) + " " ;
            }

            population[i][0] = Integer.toString(i);
            population[i][1] = individual;
        }
    }

    public void qualityOfTheIndividual(Wallets wallets){
        // La calidad del individuo es lo que da la funcion objetivo de wallets

        for (int i = 0; i < couples.length; i++) {
            String [] individual = population[i][1].split(" ");
            double [] x = new double[individual.length];

            for (int j = 0; j < individual.length; j++) {
                x[j] = Double.parseDouble(individual[j]);
            }

            population[i][2] = (Double.toString(wallets.objetive(x)));
            sum += wallets.objetive(x);
        }
    }

    public void adaptability(){
        // La adaptabilidad es la calidad de un individuo entre la sumatorio de la calidad de todos los individuo

        for (int i = 0; i < couples.length; i++) {
            population[i][3] = "" + (Double.parseDouble(population[i][2])/sum);
        }
    }

    public void couplesSelection(){
        //Selecciona las parejas para el torneo, 0 con 5, 1 con 4, 2 con 3, sin la longitud de couples es 5
        System.out.println("Seleccionando parejas...\n");

        for (int i = 0; i < couples.length; i++) {
            couples[(couples.length-1)-i] = population[i][0];
        }
    }

    public void tournament(){
        // Determina los n ganadores, con los de mayor adaptabilidad

        /*Nota: No se si es asi el cripterio de evaluacion*/

        String A = "", coupleA = "", B = "";
        int indP = 0;

        for (int i = 0; i < couples.length/2; i++) {

            A = population[i][3];
            coupleA = couples[i];
            B = population[Integer.parseInt(coupleA)][3];

            if(Double.parseDouble(A) >= Double.parseDouble(B))
                winners[indP] = population[i][0];
            else
                winners[indP] = coupleA;

            indP++;
        }
    }

    public void viewWinners(){
        //Muestra los ganadores del torneo, los que sobrevivieron
        System.out.println("Ganadores del torneo");
        int winner;

        for (String s : winners) {
            winner = Integer.parseInt(s);

            System.out.println(
                    "[" +
                    population[winner][0] +
                    "] [" +
                    population[winner][1] +
                    "] [" +
                    population[winner][2] +
                    "] [" +
                    population[winner][3] +
                            "]"
            );
        }
    }

    public void copied(){
        //Crea una nueva poblacion con los ganadores del torneo

        System.out.println("Copiando los ganadores");
        int index = 0, t = 0;

        for (int i = 0; i < winners.length; i++) {

            int winner = Integer.parseInt(winners[i]);

            temporary_population[index][0] = "" + (i + t);
            temporary_population[index + 1][0] = "" + (i + 1 + t);

            for (int j = 1; j < columns; j++) {
                temporary_population[index][j] = population[winner][j];
                temporary_population[index + 1][j] = population[winner][j];
            }
            index += 2;
            t++;
        }

        for (int i = 0; i < couples.length; i++) {
            population[i][0] = temporary_population[i][0];
            population[i][1] = temporary_population[i][1];
        }
    }

    public void combination(){

        /*Crea un nuevo indivio*/

        System.out.println("Se presento una combinacion...");
        Random ri = new Random();
        int crossPoint;
        String [] individual1, individual2;
        String coupleA = "";

        for (int i = 0; i < couples.length/2; i++) {
            individual1 = population[i][1].split(" ");
            coupleA = couples[i];
            String cadAd = "";
            individual2 = population[Integer.parseInt(coupleA)][1].split(" ");
            crossPoint = ri.nextInt(num_wallets); //selelecciona una posicion de una de las x como punto de cruce

            System.out.println(
                    "Punto cruce: [" +
                    crossPoint +
                    "]. individuo [" +
                    population[i][0] +
                    "][" +
                    population[i][1] +
                    "][" +
                    population[Integer.parseInt(coupleA)][0] +
                    "][" +
                    population[Integer.parseInt(coupleA)][1] +
                    "]"
            );

            //Genes del primer individuo
            for (int j = 0; j < crossPoint; j++) {
                cadAd += individual1[j] + " ";
            }

            //Genes del segundo individuo
            for (int j = crossPoint; j < individual1.length; j++) {
                cadAd += individual2[j] + " ";
            }

            System.out.println("Nuevo individuo [" + cadAd + "]");

            temporary_population[i][0] = Integer.toString(i);
            temporary_population[i][1] = cadAd;
        }

        for (int i = 0; i < couples.length; i++) {
            population[i][0] = temporary_population[i][0];
            population[i][1] = temporary_population[i][1];
        }

    }

    public void mutation(){
        //Manifiesta una mutacion

        System.out.println("Se presento una mutacion...");
        Random ri = new Random();
        String [] individual;

        for (int i = 0; i < 3; i++) {

            String new_individual = "";
            int mutated  = ri.nextInt(rows); // Selecciona un individuo al azar
            individual = population[mutated][1].split(" ");
            int gen = ri.nextInt(num_wallets); // Del individuo selecciona un gen aleatorio

            individual[gen] = Double.toString(ri.nextDouble(1.0)); // Le da un nuevo valor al gen

            for (int j = 0; j < num_wallets; j++) {
                new_individual += individual[j] + " ";
            }

            population[mutated][1] = new_individual;
            System.out.println("[" + population[mutated][0] + "][" + population[mutated][1] + "]");
        }
    }

    public void SearchBest(){

        int best_position = -1;
        double max_coef = -1;

        for (int i = 0; i < population.length; i++) {
            if (i == 0){
                best_position = Integer.parseInt(population[i][0]);
                max_coef = Double.parseDouble(population[i][2]);
            } else if (Double.parseDouble(population[i][2]) > max_coef) {
                best_position = Integer.parseInt(population[i][0]);
                max_coef = Double.parseDouble(population[i][2]);
            }
        }

        System.out.println("La mejor opcipn es:");
        System.out.println(
                "[" +
                        population[best_position][0] +
                        "] [" +
                        population[best_position][1] +
                        "] [" +
                        population[best_position][2] +
                        "] [" +
                        population[best_position][3] +
                        "]"
        );
    }

    public void viewPopulation(){
        // Vizualiza la poblacion
        System.out.println("=========================Poblacion actual================================");
        String str = "";

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < 4; j++) {
                str += "[" + population[i][j] + "] ";
            }

            str += "\n";
        }

        System.out.println(str);
    }

}
