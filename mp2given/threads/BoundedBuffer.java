//class BoundedBuffer
//This class implements the synchronization methods to be used in 
//the bounded buffer problem 

public class BoundedBuffer
{
   //MP2 create any variables you need
Lock lock;
Condition toRead;
Condition toWrite;
int l;
char q[];
int it, out, in;
   //BoundedBuffer
   //constructor:  initialize any variables that are needed for a bounded 
   //buffer of size "size"
   public BoundedBuffer(int size)
   {
q = new char[size];
l = size;
toRead = new Condition("start reading");
toWrite = new Condition("start writing");
lock = new Lock("buffer locked");
it = 0;
out = 0;
in = 0;
 
   }

   //produce()
   //produces a character c.  If the buffer is full, wait for an empty
   //slot
   public void produce(char c)
   {
     //MP2
lock.acquire();
while(it>=l){
toWrite.wait(lock);
} 

q[in] = c;
in=(in+1)%l;
it++;
toRead.signal(lock);
lock.release();
   }

   //consume()
   //consumes a character.  If the buffer is empty, wait for a producer.
   //use method SynchTest.addToOutputString(c) upon consuming a character. 
   //This is used to test your implementation.
   public void consume()
   {
     //MP2
     //make sure you change the following line accordingly
char c;
lock.acquire();
while(it<1){
toRead.wait(lock);

}
c=q[out];
out=(out+1)%l;
it--;
SynchTest.addToOutputString(c);
toWrite.signal(lock);
lock.release();
   }

}
