import tensorflow as tf
print(tf.__version__) # 2.7.4

gpus = tf.config.experimental.list_physical_devices('GPU')
# experimental: experimental method
# list_physical_devices 물리적인 장치 리스트
print(gpus)
# [PhysicalDevice(name='/physical_device:GPU:0', device_type='GPU')]
# Nvidia GPU만 출력됨(intel 내장형 GPU 출력 X)

print(type(gpus))
print(len(gpus))


if(gpus):
    print("GPU is running.")
else:
    print("GPU isn't running.")
# on GPU: GPU is running.
# on CPU: gpus=[], GPU isn't running.
