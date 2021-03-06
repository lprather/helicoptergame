/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.gameobjects;

import java.util.ArrayList;
import java.util.Iterator;

//collection of game objects
//modeled after Prof. Posnett's in BirdWorld series
public abstract class GameObjectCollection<T> extends GameObject
        implements Iterable<T> {

    ArrayList<T> gameObjects;

    class GameObjectIterator implements Iterator<T>{

        int index = 0;

        @Override
        public boolean hasNext() {return index < gameObjects.size();}

        @Override
        public T next() {return gameObjects.get(index);}
    }

    public GameObjectCollection(){gameObjects = new ArrayList<>();}

    public ArrayList<T> getGameObjects(){return gameObjects;}

    public void add(T gameObject){gameObjects.add(gameObject);}

    public int size(){return gameObjects.size();}

    public Iterator<T> iterator(){return new GameObjectIterator();}

}
