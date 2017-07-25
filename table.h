#include <string>
#include <utility>
#include <iostream>

using namespace std;

template <class TYPE>
class Table{
public:
	Table(){}
	virtual bool update(const string& key, const TYPE& value)=0;
	virtual bool remove(const string& key)=0;
	virtual bool find(const string& key, TYPE& value)=0;
	virtual ~Table(){}
};

template <class TYPE>
class SimpleTable:public Table<TYPE>{

	struct Record{
		TYPE data_;
		string key_;
		Record(const string& key, const TYPE& data){
			key_=key;
			data_=data;
		}

	};

	Record** records_;   //the table
	int max_;           //capacity of the array
	int size_;          //current number of records held
	int search(const string& key);
	void sort();
	void grow();
public:
	SimpleTable(int maxExpected);
	SimpleTable(const SimpleTable& other);
	SimpleTable(SimpleTable&& other);
	virtual bool update(const string& key, const TYPE& value);
	virtual bool remove(const string& key);
	virtual bool find(const string& key, TYPE& value);
	virtual const SimpleTable& operator=(const SimpleTable& other);
	virtual const SimpleTable& operator=(SimpleTable&& other);
	virtual ~SimpleTable();
};


//returns index of where key is found.
template <class TYPE>
int SimpleTable<TYPE>::search(const string& key){
	int rc=-1;
	for(int i=0;i<size_;i++){
		if(records_[i]->key_==key){
			rc=i;
		}
	}
	return rc;
}
//sort the according to key in table
template <class TYPE>
void SimpleTable<TYPE>::sort(){
   int minIdx=0;
   for(int i=0;i<size_;i++){
   		minIdx=i;
     	for(int j=i+1;j<size_;j++){
     		if(records_[j]->key_ < records_[minIdx]->key_){
     			minIdx=j;
     		}
    	}
    	Record* tmp=records_[i];
    	records_[i]=records_[minIdx];
    	records_[minIdx]=tmp;
   }
}

//grow the array by one element
template <class TYPE>
void SimpleTable<TYPE>::grow(){
	Record** newArray=new Record*[max_+1];
	max_=max_+1;
	for(int i=0;i<size_;i++){
		newArray[i]=records_[i];
	}
	delete [] records_;
	records_=newArray;
}

/* none of the code in the function definitions below are correct.  You can replace what you need
*/
template <class TYPE>
SimpleTable<TYPE>::SimpleTable(int maxExpected): Table<TYPE>(){
	records_=new Record*[maxExpected];
	max_=maxExpected;
	size_=0;
}

template <class TYPE>
SimpleTable<TYPE>::SimpleTable(const SimpleTable<TYPE>& other){
	records_=new Record*[other.max_];
	max_=other.max_;
	size_=0;
	for(int i=0;i<other.size_;i++){
		update(other.records_[i]->key_,other.records_[i]->data_);
	}
}
template <class TYPE>
SimpleTable<TYPE>::SimpleTable(SimpleTable<TYPE>&& other){
	size_=other.size_;
	max_=other.max_;
	records_=other.records_;
	other.records_= nullptr;
	other.size_=0;
	other.max_=0;
}

template <class TYPE>
bool SimpleTable<TYPE>::update(const string& key, const TYPE& value){
	int idx=search(key);
	if(idx==-1){
		if(size_ == max_){
			grow();
		}
		records_[size_++]=new Record(key,value);
		sort();
	}
	else{
		records_[idx]->data_=value;
	}
	return true;
}

template <class TYPE>
bool SimpleTable<TYPE>::remove(const string& key){
	int idx=search(key);
	if(idx!=-1){
		delete records_[idx];
		for(int i=idx;i<size_-1;i++){
			records_[i]=records_[i+1];
		}
		size_--;
		return true;
	}
	else{
		return false;
	}
}

template <class TYPE>
bool SimpleTable<TYPE>::find(const string& key, TYPE& value){
	int idx=search(key);
	if(idx==-1)
		return false;
	else{
		value=records_[idx]->data_;
		return true;
	}
}

template <class TYPE>
const SimpleTable<TYPE>& SimpleTable<TYPE>::operator=(const SimpleTable<TYPE>& other){
	if(this!=&other){
		if(records_){
			int sz=size_;
			for(int i=0;i<sz;i++){
				remove(records_[0]->key_);
			}
			delete [] records_;
		}
		records_=new Record*[other.max_];
		max_=other.max_;
		size_=0;
		for(int i=0;i<other.size_;i++){
			update(other.records_[i]->key_,other.records_[i]->data_);
		}

	}
	return *this;
}
template <class TYPE>
const SimpleTable<TYPE>& SimpleTable<TYPE>::operator=(SimpleTable<TYPE>&& other){
	swap(records_,other.records_);
	swap(size_,other.size_);
	swap(max_,other.max_);
	return *this;
}
template <class TYPE>
SimpleTable<TYPE>::~SimpleTable(){
	if(records_){
		int sz=size_;
		for(int i=0;i<sz;i++){
			remove(records_[0]->key_);
		}
		delete [] records_;
	}
}

template <class TYPE>
class LPTable:public Table<TYPE>{
    struct Record{
		TYPE data_;
		string key_;
		Record(const string& key, const TYPE& data){
			key_=key;
			data_=data;
		}

	};

	Record** myTable;   //the table
	int max_;           //capacity of the array
	int count_;          //current number of records held
public:
	LPTable(int maxExpected);
	LPTable(const LPTable& other);
	LPTable(LPTable&& other);
	int findIndex(const string& key);
	int numRecords() const; // This function returns the number of records in the table.
	bool isEmpty() const; // This function returns true if the table is empty and has no records
	bool isFull() const; // This function returns true if the table is full and no more records can be added.
	virtual bool update(const string& key, const TYPE& value);
	virtual bool remove(const string& key);
	virtual bool find(const string& key, TYPE& value);
	virtual const LPTable& operator=(const LPTable& other);
	virtual const LPTable& operator=(LPTable&& other);
	virtual ~LPTable();
};
// Overload Constructor
// Accepts the maximum expected records, doesn't return anything
template <class TYPE>
LPTable<TYPE>::LPTable(int maxExpected): Table<TYPE>(){
    if (maxExpected > 0) {
        max_ = maxExpected * 1.3;
        count_ = 0;
        myTable = new Record*[max_];
        for (int i=0; i<max_; i++)
            myTable[i] = nullptr;
    } else {
        max_ = count_ = 0;
        myTable = nullptr;
    }
}
//
template <class TYPE>
LPTable<TYPE>::LPTable(const LPTable<TYPE>& other){
        myTable = new Record*[other.max_];
        max_ = other.max_;
        for (int i=0; i<max_; i++)
            if (other.myTable[i])
                myTable[i] = new Record(other.myTable[i]->key_, other.myTable[i]->data_);
            else
                myTable[i] = nullptr;
        count_ = other.count_;
}

template <class TYPE>
LPTable<TYPE>::LPTable(LPTable<TYPE>&& other){

}
// Checks if table is empty, if it it returns ture, otherwise false
template <class TYPE>
bool LPTable<TYPE>::isEmpty() const {
    if(count_ == 0){
        return true;
    } else {
        return false;
    }
}
// Checks if the table is full. If it is, return true, if not return false. 
template <class TYPE>
bool LPTable<TYPE>::isFull() const {
    if(count_ == max_) {
        return true;
    } else {
        return false;
    }
}
// Really? Function returns the number of elements in the table
template <class TYPE>
int LPTable<TYPE>::numRecords() const {
    return count_;
}
//Basically, this is my hash function. Acceps key, returns hash index for that key.
template <class TYPE>
int LPTable<TYPE>::findIndex(const string& key){
    std::hash<std::string> myHashFunction;
    int idx = -1;
    if (max_ != 0)
        idx = myHashFunction(key) % max_;
    return idx;
}
// The function is passed a key-value pair
// If table has a record with a matching key, the records value is replaced by the value passed to the function
// If no record exist, a record with key-value pair is added to the table
// If the record was successfully added or updated, finction returns true, otherwise false is returned
template <class TYPE>
bool LPTable<TYPE>::update(const string& key, const TYPE& value){
    bool result = false;
    int idx = 0;
    idx = findIndex(key); // get the hash
    if(myTable[idx] != nullptr){
        if(myTable[idx]->key_ == key){ // if the key is the same
            myTable[idx]->data_ = value; // update the data
            result = true;
        } else if (myTable[idx]->key_ == "!EMPTY!") { // if the cell is marked as EMPTY
            myTable[idx]->key_ = key; // set the key
            myTable[idx]->data_ = value; // update the data
            count_++;
            result = true;
        } else { // not the same
            // look for empty record or another record with the same key
            int i = idx + 1;
            while (i != idx && !result) { // loop throgh the records
                if(myTable[i] == nullptr) { // if we found the empty spot
                    // then we creating new record
                    myTable[i] = new Record(key, value);
                    count_++;
                    result = true;
                } else if(myTable[i]->key_ == key){ // if we found another record with the same key
                    // then we updating the value
                    myTable[i]->data_ = value;
                    result = true;
                } else if (myTable[i]->key_ == "!EMPTY!") {
                    myTable[i]->key_ = key; // set the key
                    myTable[i]->data_ = value; // update the data
                    count_++;
                    result = true;
                }
                i = (i >= max_ - 1) ? 0 : i + 1;
            }
        }
    } else { // empty, create new record
        // check if there any space left
        //cout << "Here" << endl;
        if(!isFull()){ // if there is a space then...
            myTable[idx] = new Record(key, value);
            count_++;
            result = true;
        } else {
            result = false;
        }
    }
	return result;
}
// This function is passed a key and a reference for passing back a found value
// If table contains a record with a matching key, the function returns true
// and passes back the valur from the record
// if it doesn't find a record with a matching key, function returns false
template <class TYPE>
bool LPTable<TYPE>::find(const string& key, TYPE& value){
	int idx = 0;
	bool result = false;
    idx = findIndex(key); // get the hash for the key
    if (myTable[idx] != nullptr) { // if it's not empty
        if (myTable[idx]->key_ == key){
            value = myTable[idx]->data_;
            result = true;
        } else {
            int i = (idx == max_ - 1) ? 0 : idx + 1;
            while (i != idx && !result && myTable[i] != nullptr) {
                if(myTable[i]->key_== key){
                    value = myTable[i]->data_;
                    result = true;
                }
                i = (i >= max_ - 1) ? 0 : i + 1;
            }
        }
    } else {
        result  = false;
    }
    return result;
}
// This function is passed a key.
// If table has a record with a mathing key, we "remove" it from the table
template <class TYPE>
bool LPTable<TYPE>::remove(const string& key){
    int idx = 0;
	bool deleted = false;
    idx = findIndex(key);
    if (myTable[idx] != nullptr) { // if it's not empty
        if (myTable[idx]->key_ == key){ // if this is the corect key then ...
            myTable[idx]->key_ = "!EMPTY!"; // we "delete" it
            deleted = true;
        } else { // search for a corect key or nullptr
            int i = (idx == max_ - 1) ? 0 : idx + 1;
            while (i != idx && !deleted && myTable[i] != nullptr) {
                if(myTable[i]->key_== key){
                    myTable[i]->key_ = "!EMPTY!";
                    deleted = true;
                }
                i = (i == max_ - 1) ? 0 : i + 1;
            }
        }
    }
	return deleted;
}
// Assignment operator
template <class TYPE>
const LPTable<TYPE>& LPTable<TYPE>::operator=(const LPTable<TYPE>& other){
	if(this != &other) {
        delete[] myTable;
        max_ = other.max_;
        count_ = other.count_;

        if(other.myTable != nullptr) {
            myTable = new Record*[other.max_];
            for(int i = 0; i < max_; i++) {
                if(other.myTable[i] != nullptr) {
                    myTable[i] = new Record(other.myTable[i]->key_, other.myTable[i]->data_);
                } else {
                    myTable[i] = nullptr;
                }
            }
        } else {
            myTable = nullptr;
        }
	}
	return *this;

}
template <class TYPE>
const LPTable<TYPE>& LPTable<TYPE>::operator=(LPTable<TYPE>&& other){
    if(this != &other) {
        delete[] myTable;
        max_ = other.max_;
        count_ = other.count_;
        myTable = other.myTable;
        other.max_ = 0;
        other.count_ = 0;
        other.myTable = nullptr;
    }
	return *this;

}
template <class TYPE>
LPTable<TYPE>::~LPTable() {
    for(int i = 0; i < max_; i++) {
        if(myTable[i] != nullptr){
            delete myTable[i];
            myTable[i] = nullptr;
        }
    }
    delete[] myTable;
    myTable = nullptr;
}
