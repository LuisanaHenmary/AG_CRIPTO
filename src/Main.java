
public class Main {

    public static void main(String[] args) {

        int rows = 6, n_winners = 3, generation = 1, max_generation = 5;

        double [][] asset_r = new double[][]{
                {74.15, 74.28714, 74.37572, 75.45143},
                {1771.89, 1767.69, 1782.0, 1790.62},
                {1871.89, 1717.69, 1222.0, 190.62},
        };

        Wallets wallets = new Wallets(asset_r);
        GeneticAlgorithm ga = new GeneticAlgorithm(rows, n_winners, asset_r.length);
        ga.initPopulation();

        int randomNumber1 = (int) (Math.random() * 100);
        int randomNumber2 = (int) (Math.random() * 100);

        while (generation <= max_generation){
            System.out.println("\n\n\n GENERACION " + generation);


            ga.qualityOfTheIndividual(wallets);
            ga.adaptability();
            ga.viewPopulation();

            // Competencia para determinar que individuos sobreviven
            ga.qualityOfTheIndividual(wallets);
            ga.adaptability();
            ga.couplesSelection();
            ga.tournament();
            ga.viewWinners();

            //Nueva poblacion con los sobrevivientes
            ga.copied();
            ga.qualityOfTheIndividual(wallets);
            ga.adaptability();
            ga.viewPopulation();

            // Seccion de parejas en caso de combinacion
            ga.couplesSelection();

            if (50 <= randomNumber1 && randomNumber1 <= 90){
                System.out.println("\nProbabilidad de cruce: " + randomNumber1);
                ga.combination();
                ga.qualityOfTheIndividual(wallets);
                ga.adaptability();
                ga.viewPopulation();
            }else {
                System.out.println("\nLa probabilidad de cruza: (" +
                        randomNumber1 +") no esta entre 50 y 90. No hay cruza\n");
            }

            if (0 <= randomNumber2 && randomNumber2 <= 75){
                System.out.println("\nProbabilidad de mutacion: " + randomNumber1);
                ga.mutation();
                ga.qualityOfTheIndividual(wallets);
                ga.adaptability();
                ga.viewPopulation();
            }else {
                System.out.println("\nLa probabilidad de mutacion: (" +
                        randomNumber2 +") no esta entre 0 y 75. No hay mutacion\n");
            }

            generation++;
        }

        ga.viewPopulation();
        ga.SearchBest();
    }
}