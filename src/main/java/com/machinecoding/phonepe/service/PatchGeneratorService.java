package com.machinecoding.phonepe.service;

import com.machinecoding.phonepe.exceptions.DistortedBinaryDataException;

public interface PatchGeneratorService {

  byte[] createBinaryPatch(byte[] source, byte[] target) throws DistortedBinaryDataException;

  byte[] applyBinaryPatch(byte[] source, byte[] patch);

}
