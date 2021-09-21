import setuptools

setuptools.setup(
    name="pipeline",
    version='1.0',
    install_requirements=[
        "pandas==1.2.4",
        "numpy==1.20.2",
        "jupyter==1.0.0"
    ],
    packages=setuptools.find_packages()
)