import setuptools

setuptools.setup(
    name="pipeline",
    version='1.0',
    install_requires=[
        "pandas",
        "numpy",
        "SQLAlchemy",
        "mysql-connector-python",
    ],
    packages=setuptools.find_packages()
)