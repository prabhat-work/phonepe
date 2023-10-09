package com.machinecoding.phonepe.service.Implemenation;

import com.machinecoding.phonepe.exceptions.DistortedBinaryDataException;
import com.machinecoding.phonepe.service.PatchGeneratorService;

public class PatchGeneratorServiceImpl implements PatchGeneratorService {

    public byte[] createBinaryPatch(byte[] source, byte[] target)
        throws DistortedBinaryDataException {
      if (source.length != target.length) {
        throw new DistortedBinaryDataException("Source and target arrays must have the same length.");
      }

      byte[] patch = new byte[source.length];

      for (int i = 0; i < source.length; i++) {
        patch[i] = (byte) (target[i] - source[i]);
      }

      return patch;
    }

    public byte[] applyBinaryPatch(byte[] source, byte[] patch) {
      byte[] result = new byte[source.length];

      for (int i = 0; i < source.length; i++) {
        result[i] = (byte) (source[i] + patch[i]);
      }

      return result;
    }

}
