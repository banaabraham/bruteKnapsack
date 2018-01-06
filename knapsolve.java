import java.util.*;

class combi{	

	ArrayList<Integer[]> kombi = new ArrayList<Integer[]>(); 
  
	ArrayList<Integer[]> getKombi(){
		return kombi;
	}
	
    void Combinations(int[] sequence, int N) 
    {
        int[] data = new int[N];
        for (int r = 0; r < sequence.length; r++)
            combinations(sequence, data, 0, N - 1, 0, r);
    }
 
    void combinations(int[] sequence,int[] data,int start,int end,
            int index, int r) 
    {
        if (index == r) 
        {	Integer[] temp = new Integer[r];
            for (int j=0;j<r;j++){
            	temp[j] = data[j];
            }
            kombi.add(temp);
        }
 
        for (int i=start;i<=end&&((end-i+1)>=(r - index));i++) 
        {
            data[index] = sequence[i];
            combinations(sequence,data,i+1,end,index+1,r);
        }
        
    }
}


class solve{
	
	HashMap<Integer,Integer> berat_barang = new HashMap<Integer,Integer>();
	HashMap<Integer,Integer> nilai_barang = new HashMap<Integer,Integer>();
	HashMap<Integer,String> nama_barang = new HashMap<Integer,String>();
	int indexBarang = 0;
	void tambahBarang(String nama,int berat,int nilai){
		nama_barang.put(indexBarang, nama);
		berat_barang.put(indexBarang, berat);
		nilai_barang.put(indexBarang,nilai);
		indexBarang++;
	}
	
	void cetakBarang(){
		System.out.println("nama\tberat\tnilai");
		for(int i=0;i<indexBarang;i++){
			System.out.println(nama_barang.get(i)+"\t"+berat_barang.get(i)+"\t"+nilai_barang.get(i));		
		}
	}
	
	
	int getBerat(Integer[] barang){
		int berat = 0;
		for(int i: barang){
			berat=berat+berat_barang.get(i);
		}
		return berat;
	}
	
	
	int getNilai(Integer[] barang){
		int nilai = 0;
		for(int i: barang){
			nilai=nilai+nilai_barang.get(i);
		}
		return nilai;
	}
	
	void getBest(int maksberat,int deviasi){
		double maksRatio = 0.0;
		int[] seq = new int[indexBarang];
		for(int i=0;i<indexBarang;i++){
			seq[i]=i;
		}
			
		combi sack = new combi();
		sack.Combinations(seq, indexBarang);
		ArrayList<Integer[]> daftar = sack.getKombi();
		Integer[] hasil = new Integer[indexBarang];
		for(int i=0;i<daftar.size();i++){
			try{
				if(getNilai(daftar.get(i))/getBerat(daftar.get(i)) > maksRatio && (maksberat-getBerat(daftar.get(i)))<deviasi && (maksberat-getBerat(daftar.get(i)))>=0 ){
					maksRatio = getNilai(daftar.get(i))/getBerat(daftar.get(i));
					hasil = daftar.get(i);
				}
			}catch(Exception e){
				continue;
			}
			
		}
		
		System.out.println(maksRatio);
		for(int i : hasil){
			System.out.println(i);
		}
	}
}


public class knapsack {

	public static void main(String[] args) {
		
		solve knap = new solve();
		knap.tambahBarang("akua",5, 150);
		knap.tambahBarang("sikat gigi", 7, 220);
		knap.tambahBarang("obeng", 13, 520);
		knap.tambahBarang("kopi", 15, 250);
		knap.tambahBarang("boneka", 4, 40);
		knap.getBest(20,5);
	}

}
