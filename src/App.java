import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Part 1 - Setting Up Memories
        System.out.println("Choose memory mapping type: ");
        System.out.println("1. Direct Mapping");
        System.out.println("2. Associative Mapping");
        int mappingType = scanner.nextInt();
        System.out.println("Choose a value for MM in bytes: ");
        int mainMemorySizeBytes = scanner.nextInt();
        System.out.println("Choose a value for CACHE in bytes: ");
        int cacheSizeBytes = scanner.nextInt();
        System.out.println("Choose a value for BlockSize in bytes: ");
        int blockSizeBytes = scanner.nextInt();

        // Output chosen mapping type
        if (mappingType == 1) {
            System.out.println("Chosen mapping: Direct Mapping");
        } else if (mappingType == 2) {
            System.out.println("Chosen mapping: Associative Mapping");
        }

        // Calculate parameters
        int blockOffsetBits = (int) (Math.log(blockSizeBytes) / Math.log(2));
        int totalBlocks = mainMemorySizeBytes / blockSizeBytes;
        int indexBits = (int) (Math.log(totalBlocks) / Math.log(2));
        int tagBits = 32 - indexBits - blockOffsetBits;

        System.out.println("Number of bits used in TAG: " + tagBits);
        if (mappingType == 1) {
            System.out.println("Number of bits used in LINE: " + indexBits);
        } else {
            System.out.println("Number of bits used in LINE: 0");
        }
        System.out.println("Number of bits used in WORD: " + blockOffsetBits);
        // Part 2 - Cache Hit and Cache Miss
        System.out.println("Enter sequence of addresses in hexadecimal (separated by space): ");
        scanner.nextLine(); // consume newline
        String[] addressSeq = scanner.nextLine().split(" ");
        Set<String> cache = new HashSet<>();// stores the values of cache memory
        List<String> hits = new ArrayList<>();// register adresses that resulted in cache hit
        List<String> misses = new ArrayList<>();// register adresses that resulted in cache misses

        for (String address : addressSeq) {
            if (cache.contains(address)) {
                hits.add(address);
            } else {
                misses.add(address);
                cache.add(address);
                // Verifies if the cache is full
                if (cache.size() > cacheSizeBytes / blockSizeBytes) { //
                    // get the oldest address in the cache
                    Iterator<String> iterator = cache.iterator();
                    String oldestAddress = iterator.next();
                    iterator.remove(); // Remove the oldest address from the cache
                    misses.add(oldestAddress); // Adds the oldest address to the misses list
                }
            }
        }

        // Output Cache Hits
        System.out.println("Cache Hits: " + hits.size());
        System.out.println("Addresses generating Cache Hit: " + hits);

        // Output Cache Misses
        System.out.println("Cache Misses: " + misses.size());
        System.out.println("Addresses generating Cache Miss: " + misses);

        scanner.close();
    }
}
