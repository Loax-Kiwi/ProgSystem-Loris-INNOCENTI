import java.util.*;

public class VirtualFileSystem {

    private MemoryManager memoryManager;

    public VirtualFileSystem() {
        this.memoryManager = new MemoryManager();
    }

    private int allocateInode() {
        for (int i = 0; i < MemoryManager.MAX_INODES; i++) {
            Inode inode = new Inode(memoryManager, i);

            if (inode.getFileType() == 0) {
                return i;
            }
        }

        return -1;
    }

    public boolean createFile(String directory, String filename) {
        int inodeNum = allocateInode();

        if (inodeNum == -1) {
            return false;
        }

        Inode inode = new Inode(memoryManager, inodeNum);
        long currentTime = System.currentTimeMillis();

        int[] directPointers = new int[Inode.DIRECT_POINTERS];
        Arrays.fill(directPointers, -1);

        inode.writeToMemory(
                1,
                0,
                currentTime,
                currentTime,
                directPointers,
                -1,
                (short) 0644,
                1
        );

        return true;
    }

    public MemoryManager getMemoryManager() {
        return memoryManager;
    }
}