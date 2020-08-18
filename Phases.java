package phasers.project1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Phases {
    public static ConcurrentLinkedQueue<StringBuffer> phase1(){
        ConcurrentLinkedQueue<StringBuffer> concurrentLinkedQueue =
                new ConcurrentLinkedQueue<>();

        for(int i = 0; i < 10; i++){
            Random random = new Random();
            StringBuffer stringBuffer = new StringBuffer();
            for (int j = 0; j < 5; j++) {
                char ranChar = (char) random.nextInt(100);
                stringBuffer.append(ranChar);
            }
            concurrentLinkedQueue.add(stringBuffer);
        }

        return concurrentLinkedQueue;
    }

    public static CopyOnWriteArrayList<ByteBuffer> phase2(
            ConcurrentLinkedQueue<StringBuffer> stringBuffers){
        CopyOnWriteArrayList<ByteBuffer> buffers =
                new CopyOnWriteArrayList<>();

        for (int i = 0; i < stringBuffers.size(); i++){
            StringBuffer stringBuffer = stringBuffers.poll();
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2048);

            for(int j = 0; j < stringBuffer.length(); j++)
                byteBuffer.put((byte) stringBuffer.charAt(j));

            byteBuffer.rewind();
            buffers.add(byteBuffer);
        }

        return buffers;
    }

    public static void phase3(CopyOnWriteArrayList<ByteBuffer> buffers,
                              String name){
        try(FileChannel fileChannel =
                    (FileChannel) Files.newByteChannel(
                            Path.of("C:\\" +
                                    "Users\\" +
                                    "Emman\\" +
                                    "IdeaProjects\\" +
                                    "CouncurrencyUtilities\\" +
                                    "src\\" +
                                    "phasers\\" +
                                    "project1\\"
                                     + name + ".txt"),
                            StandardOpenOption.WRITE,
                            StandardOpenOption.CREATE
                    )){

            for (ByteBuffer buffer : buffers)
                fileChannel.write(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
