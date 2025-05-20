import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * You are given a list of iterators. An iterator generally has two methods -- hasNext()  and next() .
 * <p>
 * List<Iterator<Integer>>
 * Eg:
 * {
 * Itr1: 1,2,3
 * Itr2: 4,5,6
 * Itr3: 7,8,9
 * }
 * <p>
 * Can you implement an Round robin flattening iterator ? You need to implement both the methods.
 * <p>
 * For eg: iterator.next() should return the following:
 * 1,4,7,2,5,8,3,6,9
 */
public class IteratorCustom {

    public static void main(String[] args) {
        List<Iterator<Integer>> iters = new ArrayList<>();

        List<Integer> l2 = new ArrayList<>();
        l2.add(1);
        l2.add(2);
        iters.add(l2.iterator());

        List<Integer> l1 = new ArrayList<>();
        l1.add(3);
        iters.add(l1.iterator());

        List<Integer> l3 = new ArrayList<>();
        l3.add(4);
        l3.add(5);
        l3.add(6);
        iters.add(l3.iterator());

        try {
            IteratorWrapper iteratorWrapper = new IteratorWrapper(iters);
            System.out.println("Case-1");
            while (iteratorWrapper.hasNext()) {
                iteratorWrapper.hasNext();
                System.out.println(iteratorWrapper.next());
            }

            System.out.println("Case-2");
            iteratorWrapper = new IteratorWrapper(null);
            while (iteratorWrapper.hasNext()) {
                iteratorWrapper.hasNext();
                System.out.println(iteratorWrapper.next());
            }

            System.out.println("Case-3");
            iters = new ArrayList<>();
            List<Integer> l4 = new ArrayList<>();
            iters.add(l4.iterator());
            iteratorWrapper = new IteratorWrapper(iters);
            while (iteratorWrapper.hasNext()) {
                iteratorWrapper.hasNext();
                System.out.println(iteratorWrapper.next());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class IteratorWrapper implements Iterator<Integer> {
        private int currentIterator = 0;
        private List<Iterator<Integer>> list;

        public IteratorWrapper(List<Iterator<Integer>> iteratorList) {
            if (iteratorList == null || iteratorList.isEmpty()) {
                list = new ArrayList<>();
            } else {
                this.list = iteratorList;
            }
        }

        @Override
        public boolean hasNext() {
            int checkedIterators = 0;
            while (checkedIterators < list.size() && !list.get(currentIterator).hasNext()) {
                currentIterator = (currentIterator + 1) % list.size();
                checkedIterators += 1;
            }
            return list.size() > currentIterator && list.get(currentIterator).hasNext();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Integer elem = list.get(currentIterator).next();
            currentIterator = (currentIterator + 1) % list.size();
            return elem;
        }
    }

    private static class IteratorWrapperImprov implements Iterator<Integer> {
        private List<Iterator<Integer>> list;
        private Iterator<Iterator<Integer>> listIter;

        public IteratorWrapperImprov(List<Iterator<Integer>> iteratorList) {
            if (iteratorList == null || iteratorList.isEmpty()) {
                list = new ArrayList<>();
            } else {
                this.list = new ArrayList<>();
                for(Iterator<Integer> iterator : iteratorList) {
                    if(iterator.hasNext()) {
                        list.add(iterator);
                    }
                }
            }
            listIter = list.iterator();
        }

        @Override
        public boolean hasNext() {
            if(listIter.hasNext()) {
                return true;
            }

            List<Iterator<Integer>> copy = new ArrayList<>();
            for(Iterator<Integer> iterator : list) {
                if(iterator.hasNext()) {
                    copy.add(iterator);
                }
            }
            this.list = copy;
            this.listIter = list.iterator();
            return listIter.hasNext();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return listIter.next().next();
        }
    }

}
