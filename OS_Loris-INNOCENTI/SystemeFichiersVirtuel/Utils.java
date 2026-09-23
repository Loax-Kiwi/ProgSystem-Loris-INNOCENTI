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

}
