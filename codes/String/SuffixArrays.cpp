#include <iostream>
#include <string>
using namespace std;

class SuffixArrays
{
	private:
		string* arr;
		int n;
		bool is_sorted;

		void three_way_quicksort(int i,int j,int d)
		{

		}

	public:
		SuffixArrays(string inp)
		{
			n = inp.size();
			arr = new string[n];
			for(int i=0;i<n;i++)
				arr[i] = inp.substr(i);
			is_sorted = false;
		}

		bool sorted()
		{	return is_sorted; }

		void sortSuffix()
		{

		}

		strig lrs()
		{

		}
};