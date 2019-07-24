package newfolder;

public class ProofofWork {

	public ProofofWork() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int [][] Arr1 = new int [3][3];

         for (int i = 0; i < 3; i++) {

                     for (int j = 0; j < 3; j++) {

                                 Arr1[i][j] = j+(3*i+1);

                                 System.out.print(Arr1[i][j] + " ");

                     }

                     System.out.println();

         }

         System.out.println();

		}

}
