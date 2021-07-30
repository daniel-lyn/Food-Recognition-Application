package com.example.healthydiet.classifier;

import android.app.Activity;
import android.graphics.Bitmap;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.TensorProcessor;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp;
import org.tensorflow.lite.support.label.TensorLabel;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ImageClassifier {

    private final Interpreter tensorClassifier;
    private final int imageResizeX;
    private final int imageResizeY;
    private final List<String> labels;
    private TensorImage inputImageBuffer;
    private final TensorBuffer probabilityImageBuffer;
    private final TensorProcessor probabilityProcessor;

    public ImageClassifier(Activity activity) throws IOException {
        //load model
        @NonNull MappedByteBuffer classifierModel = FileUtil.loadMappedFile(activity,
                "model-export_icn_tflite-food_recogn-2021-03-20T18_28_55.513682Z_model.tflite");
        labels = FileUtil.loadLabels(activity, "model-export_icn_tflite-food_recogn-2021-03-20T18_28_55.513682Z_dict.txt");

        tensorClassifier = new Interpreter(classifierModel, null);

        int imageTensorIndex = 0; //input
        int probabilityTensorIndex = 0; //output

        int[] inputImageShape = tensorClassifier.getInputTensor(imageTensorIndex).shape();
        DataType inputDataType = tensorClassifier.getInputTensor(imageTensorIndex).dataType();

        int[] outputImageShape = tensorClassifier.getOutputTensor(probabilityTensorIndex).shape();
        DataType outputDataType = tensorClassifier.getOutputTensor(probabilityTensorIndex).dataType();

        imageResizeX = inputImageShape[1];
        imageResizeY = inputImageShape[2];

        inputImageBuffer = new TensorImage(inputDataType);

        probabilityImageBuffer = TensorBuffer.createFixedSize(outputImageShape, outputDataType);

        probabilityProcessor = new TensorProcessor.Builder().add(new NormalizeOp(0.0f, 255.0f)).build();
    }

    public List<Recognition> recognizeImage(final Bitmap bitmap){
        List<Recognition> recognitions = new ArrayList<>();
        inputImageBuffer = loadImage(bitmap);
        tensorClassifier.run(inputImageBuffer.getBuffer(), probabilityImageBuffer.getBuffer().rewind());
        Map<String, Float> labelledProbability = new TensorLabel(labels,
                probabilityProcessor.process(probabilityImageBuffer)).getMapWithFloatValue();
        for(Map.Entry<String,Float> entry : labelledProbability.entrySet()){
            recognitions.add(new Recognition(entry.getKey(), entry.getValue()*100));
        }
        // sorting predictions
        Collections.sort(recognitions);
        //return top 5 prediction
        return recognitions.subList(0, 5);
    }

    private TensorImage loadImage(Bitmap bitmap) {
        inputImageBuffer.load(bitmap);
        int cropSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
        ImageProcessor imageProcessor = new ImageProcessor.Builder()
                .add(new ResizeWithCropOrPadOp(cropSize,cropSize))
                .add(new ResizeOp(imageResizeX,imageResizeY, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR))
                .add(new NormalizeOp(0.0f, 1.0f))
                .build();
        System.out.println("HIHI");
        return imageProcessor.process(inputImageBuffer);
    }

    public class Recognition implements Comparable {
        private String name;
        private float confidence;


        public Recognition(String name, float confidence) {
            this.name = name;
            this.confidence = confidence;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "Recognition{" +
                    "name='" + name + '\'' +
                    ", confidence=" + confidence +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            return Float.compare(((Recognition)o).confidence, this.confidence);
        }
    }
}
