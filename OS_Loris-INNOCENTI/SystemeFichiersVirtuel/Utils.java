public class Utils {

    public static int writeInt(byte[] memory, int offset, int value) {

        memory[offset]     = (byte) (value >>> 24);
        memory[offset + 1] = (byte) (value >>> 16);
        memory[offset + 2] = (byte) (value >>> 8);
        memory[offset + 3] = (byte) value;

        return 4;
    }

    public static int readInt(byte[] memory, int offset) {

        byte b1 = memory[offset];
        byte b2 = memory[offset + 1];
        byte b3 = memory[offset + 2];
        byte b4 = memory[offset + 3];

        return ((b1 & 0xFF) << 24)
            | ((b2 & 0xFF) << 16)
            | ((b3 & 0xFF) << 8)
            | (b4 & 0xFF);
    }


    public static int writeShort(byte[] memory, int offset, short value) {
        memory[offset]     = (byte) (value >>> 8);
        memory[offset + 1] = (byte) value;
        return 2;
    }

    public static short readShort(byte[] memory, int offset) {
        byte b1 = memory[offset];
        byte b2 = memory[offset + 1];

        return (short) (((b1 & 0xFF) << 8)
                      | (b2 & 0xFF));
    }

    public static int writeLong(byte[] memory, int offset, long value) {
        memory[offset]     = (byte) (value >>> 56);
        memory[offset + 1] = (byte) (value >>> 48);
        memory[offset + 2] = (byte) (value >>> 40);
        memory[offset + 3] = (byte) (value >>> 32);
        memory[offset + 4] = (byte) (value >>> 24);
        memory[offset + 5] = (byte) (value >>> 16);
        memory[offset + 6] = (byte) (value >>> 8);
        memory[offset + 7] = (byte) value;

        return 8;
    }

    public static long readLong(byte[] memory, int offset) {
        long b1 = memory[offset] & 0xFF;
        long b2 = memory[offset + 1] & 0xFF;
        long b3 = memory[offset + 2] & 0xFF;
        long b4 = memory[offset + 3] & 0xFF;
        long b5 = memory[offset + 4] & 0xFF;
        long b6 = memory[offset + 5] & 0xFF;
        long b7 = memory[offset + 6] & 0xFF;
        long b8 = memory[offset + 7] & 0xFF;

        return (b1 << 56) | (b2 << 48) | (b3 << 40) | (b4 << 32)
             | (b5 << 24) | (b6 << 16) | (b7 << 8)  | b8;
    }

    public static int writeString(byte[] memory, int offset, String str, int maxLength) {
        byte[] bytes = str.getBytes();

        int indice = 0;
        while (indice < bytes.length && indice < maxLength) {
            memory[offset + indice] = bytes[indice];
            indice++;
        }

        while (indice < maxLength) {
            memory[offset + indice] = 0;
            indice++;
        }

        return maxLength;
    }

    public static String readString(byte[] memory, int offset, int maxLength) {
        int taille = 0;

        while (taille < maxLength && memory[offset + taille] != 0) {
            taille++;
        }

        return new String(memory, offset, taille);
    }
}
